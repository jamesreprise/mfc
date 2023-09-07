{ pkgs ? import <nixpkgs> { } }:
with pkgs;

stdenv.mkDerivation {
  name = "clj-env";
  nativeBuildInputs = [
    clj
  ];

  buildInputs = [
  ];
}