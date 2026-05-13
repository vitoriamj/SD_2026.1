@echo off
REM Gera os arquivos Python hello_pb2.py e hello_pb2_grpc.py
REM a partir do contrato localizado na pasta ..\proto.

python -m grpc_tools.protoc -I..\proto --python_out=. --grpc_python_out=. ..\proto\hello.proto

echo.
echo Stubs Python gerados com sucesso.
echo Arquivos esperados:
echo - hello_pb2.py
echo - hello_pb2_grpc.py
