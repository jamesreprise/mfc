package main

import (
	"mfc/client/cli"

	"errors"
	"fmt"
	"os"
	"os/signal"
	"syscall"

	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/log"
	"github.com/charmbracelet/ssh"
	"github.com/charmbracelet/wish"
)

const (
	host = "localhost"
	port = 7777
)

func main() {
	// cli := tea.NewProgram(cli.Model())
	server, err := wish.NewServer(
		wish.WithAddress(fmt.Sprint(host, ":", port)),
		// wish.WithMiddleware(cli)
	)
	if err != nil {
		println("Failed to create SSH server.")
	}
	done := make(chan os.Signal, 1)
	signal.Notify(done, os.Interrupt, syscall.SIGINT, syscall.SIGTERM)
	log.Info("Starting SSH server...", "host", host, "port", port)
	go func() {
		if err = server.ListenAndServe(); err != nil && !errors.Is(err, ssh.ErrServerClosed) {
			log.Error("Could not start server.", "error", err)
			done <- nil
		}
	}()

	<-done
}
