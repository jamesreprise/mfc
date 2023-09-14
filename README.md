# MFC

## Architecture
Users SSH into a server, which runs the client. The client connects to the game server, which runs alongside the SSH server.

## Installation
Only NixOS is supported. Add the flake as an input and enable the relevant service.

## Development
Run `nix develop` to download the appropriate dependencies.
