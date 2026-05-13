#!/usr/bin/env bash
set -e

python -m grpc_tools.protoc \
  -I../proto \
  --python_out=. \
  --grpc_python_out=. \
  ../proto/hello.proto

echo "Stubs Python gerados com sucesso."
