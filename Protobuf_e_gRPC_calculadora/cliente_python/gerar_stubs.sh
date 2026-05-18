#!/usr/bin/env bash
set -e
python -m grpc_tools.protoc -I../proto --python_out=. --grpc_python_out=. ../proto/calculadora.proto
echo "Stubs Python gerados com sucesso."