{
  description = "MFC";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { flake-utils, nixpkgs, ... }:
    flake-utils.lib.eachDefaultSystem (system:
        let pkgs = import nixpkgs { inherit system; }; 
        in
        {
          devShells.default = pkgs.mkShell {
            name = "MFC Shell";
            buildInputs = with pkgs; [ clojure leiningen ];
          };
        } 
      );
}
