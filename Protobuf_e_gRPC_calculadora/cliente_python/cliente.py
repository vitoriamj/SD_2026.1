import grpc
import calculadora_pb2
import calculadora_pb2_grpc
import os # Importado para permitir a limpeza da tela

def limpar_tela():
    # Limpa o terminal para o menu não ficar rolando infinitamente para baixo
    os.system('cls' if os.name == 'nt' else 'clear')

def escolher_operador() -> int:
    print("\n--- Calculadora Distribuída gRPC ---")
    print("1 - Somar")
    print("2 - Subtrair")
    print("3 - Multiplicar")
    print("4 - Dividir")
    print("5 - Potência (X^y)")
    print("6 - Módulo (Resto da divisão)")
    print("7 - Raiz Quadrada")
    print("0 - Sair")
    
    # Proteção caso o usuário digite uma letra por engano
    try:
        return int(input("Escolha a operação: "))
    except ValueError:
        return -1

def main() -> None:
    # Canal inseguro, exatamente como no projeto Hello World original
    with grpc.insecure_channel("localhost:50051") as channel:
        stub = calculadora_pb2_grpc.CalculadoraStub(channel)

        while True:
            limpar_tela() # Limpa o console antes de exibir o menu
            
            op = escolher_operador()
            if op == 0:
                print("Encerrando cliente...")
                break
            
            if op not in range(1, 8):
                print("\nOperação inválida. Tente novamente.")
                input("Pressione [ENTER] para continuar...")
                continue

            print() # Linha em branco para estética
            a = float(input("Digite o primeiro número: "))
            
            b = 0.0
            # Raiz quadrada só precisa de um número, não perguntamos o segundo
            if op != 7:
                b = float(input("Digite o segundo número: "))

            request = calculadora_pb2.OperacaoRequest(a=a, b=b)

            try:
                if op == 1:
                    response = stub.Somar(request)
                elif op == 2:
                    response = stub.Subtrair(request)
                elif op == 3:
                    response = stub.Multiplicar(request)
                elif op == 4:
                    response = stub.Dividir(request)
                elif op == 5:
                    response = stub.Potencia(request)
                elif op == 6:
                    response = stub.Modulo(request)
                elif op == 7:
                    response = stub.RaizQuadrada(request)

                print("\n==============================")
                print(f"Resultado : {response.resultado}")
                print(f"Mensagem  : {response.mensagem}")
                if response.erro:
                    print(f"Atenção   : Ocorreu um erro!")
                print("==============================")

            except grpc.RpcError as e:
                print(f"\n[ERRO gRPC] A comunicação com o servidor falhou: {e.details()}")
            
            # --- AJUSTE SOLICITADO ---
            # O código para aqui e espera você ler o resultado.
            input("\nPressione [ENTER] para voltar ao menu principal...")

if __name__ == "__main__":
    main()