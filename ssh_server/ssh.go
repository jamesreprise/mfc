package main

import (
	"errors"
	"fmt"
	"os"
	"os/signal"
	"syscall"

	"github.com/charmbracelet/log"
	"github.com/charmbracelet/ssh"
	"github.com/charmbracelet/wish"
)

const (
	host = "localhost"
	port = "7777"
)

func main() {
	server, err := wish.NewServer(wish.WithAddress(fmt.Sprint("%s:%d", host, port)))
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
