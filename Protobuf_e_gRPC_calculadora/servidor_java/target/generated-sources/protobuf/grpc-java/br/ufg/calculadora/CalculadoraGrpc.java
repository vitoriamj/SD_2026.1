package br.ufg.calculadora;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class CalculadoraGrpc {

  private CalculadoraGrpc() {}

  public static final java.lang.String SERVICE_NAME = "calculadora.v1.Calculadora";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getSomarMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Somar",
      requestType = br.ufg.calculadora.OperacaoRequest.class,
      responseType = br.ufg.calculadora.OperacaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getSomarMethod() {
    io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse> getSomarMethod;
    if ((getSomarMethod = CalculadoraGrpc.getSomarMethod) == null) {
      synchronized (CalculadoraGrpc.class) {
        if ((getSomarMethod = CalculadoraGrpc.getSomarMethod) == null) {
          CalculadoraGrpc.getSomarMethod = getSomarMethod =
              io.grpc.MethodDescriptor.<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Somar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculadoraMethodDescriptorSupplier("Somar"))
              .build();
        }
      }
    }
    return getSomarMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getSubtrairMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Subtrair",
      requestType = br.ufg.calculadora.OperacaoRequest.class,
      responseType = br.ufg.calculadora.OperacaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getSubtrairMethod() {
    io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse> getSubtrairMethod;
    if ((getSubtrairMethod = CalculadoraGrpc.getSubtrairMethod) == null) {
      synchronized (CalculadoraGrpc.class) {
        if ((getSubtrairMethod = CalculadoraGrpc.getSubtrairMethod) == null) {
          CalculadoraGrpc.getSubtrairMethod = getSubtrairMethod =
              io.grpc.MethodDescriptor.<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Subtrair"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculadoraMethodDescriptorSupplier("Subtrair"))
              .build();
        }
      }
    }
    return getSubtrairMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getMultiplicarMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Multiplicar",
      requestType = br.ufg.calculadora.OperacaoRequest.class,
      responseType = br.ufg.calculadora.OperacaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getMultiplicarMethod() {
    io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse> getMultiplicarMethod;
    if ((getMultiplicarMethod = CalculadoraGrpc.getMultiplicarMethod) == null) {
      synchronized (CalculadoraGrpc.class) {
        if ((getMultiplicarMethod = CalculadoraGrpc.getMultiplicarMethod) == null) {
          CalculadoraGrpc.getMultiplicarMethod = getMultiplicarMethod =
              io.grpc.MethodDescriptor.<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Multiplicar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculadoraMethodDescriptorSupplier("Multiplicar"))
              .build();
        }
      }
    }
    return getMultiplicarMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getDividirMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Dividir",
      requestType = br.ufg.calculadora.OperacaoRequest.class,
      responseType = br.ufg.calculadora.OperacaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getDividirMethod() {
    io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse> getDividirMethod;
    if ((getDividirMethod = CalculadoraGrpc.getDividirMethod) == null) {
      synchronized (CalculadoraGrpc.class) {
        if ((getDividirMethod = CalculadoraGrpc.getDividirMethod) == null) {
          CalculadoraGrpc.getDividirMethod = getDividirMethod =
              io.grpc.MethodDescriptor.<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Dividir"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculadoraMethodDescriptorSupplier("Dividir"))
              .build();
        }
      }
    }
    return getDividirMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getPotenciaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Potencia",
      requestType = br.ufg.calculadora.OperacaoRequest.class,
      responseType = br.ufg.calculadora.OperacaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getPotenciaMethod() {
    io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse> getPotenciaMethod;
    if ((getPotenciaMethod = CalculadoraGrpc.getPotenciaMethod) == null) {
      synchronized (CalculadoraGrpc.class) {
        if ((getPotenciaMethod = CalculadoraGrpc.getPotenciaMethod) == null) {
          CalculadoraGrpc.getPotenciaMethod = getPotenciaMethod =
              io.grpc.MethodDescriptor.<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Potencia"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculadoraMethodDescriptorSupplier("Potencia"))
              .build();
        }
      }
    }
    return getPotenciaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getModuloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Modulo",
      requestType = br.ufg.calculadora.OperacaoRequest.class,
      responseType = br.ufg.calculadora.OperacaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getModuloMethod() {
    io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse> getModuloMethod;
    if ((getModuloMethod = CalculadoraGrpc.getModuloMethod) == null) {
      synchronized (CalculadoraGrpc.class) {
        if ((getModuloMethod = CalculadoraGrpc.getModuloMethod) == null) {
          CalculadoraGrpc.getModuloMethod = getModuloMethod =
              io.grpc.MethodDescriptor.<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Modulo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculadoraMethodDescriptorSupplier("Modulo"))
              .build();
        }
      }
    }
    return getModuloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getRaizQuadradaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RaizQuadrada",
      requestType = br.ufg.calculadora.OperacaoRequest.class,
      responseType = br.ufg.calculadora.OperacaoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest,
      br.ufg.calculadora.OperacaoResponse> getRaizQuadradaMethod() {
    io.grpc.MethodDescriptor<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse> getRaizQuadradaMethod;
    if ((getRaizQuadradaMethod = CalculadoraGrpc.getRaizQuadradaMethod) == null) {
      synchronized (CalculadoraGrpc.class) {
        if ((getRaizQuadradaMethod = CalculadoraGrpc.getRaizQuadradaMethod) == null) {
          CalculadoraGrpc.getRaizQuadradaMethod = getRaizQuadradaMethod =
              io.grpc.MethodDescriptor.<br.ufg.calculadora.OperacaoRequest, br.ufg.calculadora.OperacaoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RaizQuadrada"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufg.calculadora.OperacaoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculadoraMethodDescriptorSupplier("RaizQuadrada"))
              .build();
        }
      }
    }
    return getRaizQuadradaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculadoraStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculadoraStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculadoraStub>() {
        @java.lang.Override
        public CalculadoraStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculadoraStub(channel, callOptions);
        }
      };
    return CalculadoraStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static CalculadoraBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculadoraBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculadoraBlockingV2Stub>() {
        @java.lang.Override
        public CalculadoraBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculadoraBlockingV2Stub(channel, callOptions);
        }
      };
    return CalculadoraBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculadoraBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculadoraBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculadoraBlockingStub>() {
        @java.lang.Override
        public CalculadoraBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculadoraBlockingStub(channel, callOptions);
        }
      };
    return CalculadoraBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalculadoraFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculadoraFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculadoraFutureStub>() {
        @java.lang.Override
        public CalculadoraFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculadoraFutureStub(channel, callOptions);
        }
      };
    return CalculadoraFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void somar(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSomarMethod(), responseObserver);
    }

    /**
     */
    default void subtrair(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubtrairMethod(), responseObserver);
    }

    /**
     */
    default void multiplicar(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMultiplicarMethod(), responseObserver);
    }

    /**
     */
    default void dividir(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDividirMethod(), responseObserver);
    }

    /**
     */
    default void potencia(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPotenciaMethod(), responseObserver);
    }

    /**
     */
    default void modulo(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getModuloMethod(), responseObserver);
    }

    /**
     */
    default void raizQuadrada(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRaizQuadradaMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Calculadora.
   */
  public static abstract class CalculadoraImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CalculadoraGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Calculadora.
   */
  public static final class CalculadoraStub
      extends io.grpc.stub.AbstractAsyncStub<CalculadoraStub> {
    private CalculadoraStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculadoraStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculadoraStub(channel, callOptions);
    }

    /**
     */
    public void somar(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSomarMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subtrair(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubtrairMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void multiplicar(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMultiplicarMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void dividir(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDividirMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void potencia(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPotenciaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void modulo(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getModuloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void raizQuadrada(br.ufg.calculadora.OperacaoRequest request,
        io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRaizQuadradaMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Calculadora.
   */
  public static final class CalculadoraBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<CalculadoraBlockingV2Stub> {
    private CalculadoraBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculadoraBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculadoraBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse somar(br.ufg.calculadora.OperacaoRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSomarMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse subtrair(br.ufg.calculadora.OperacaoRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSubtrairMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse multiplicar(br.ufg.calculadora.OperacaoRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getMultiplicarMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse dividir(br.ufg.calculadora.OperacaoRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDividirMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse potencia(br.ufg.calculadora.OperacaoRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getPotenciaMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse modulo(br.ufg.calculadora.OperacaoRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getModuloMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse raizQuadrada(br.ufg.calculadora.OperacaoRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getRaizQuadradaMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service Calculadora.
   */
  public static final class CalculadoraBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CalculadoraBlockingStub> {
    private CalculadoraBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculadoraBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculadoraBlockingStub(channel, callOptions);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse somar(br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSomarMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse subtrair(br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubtrairMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse multiplicar(br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMultiplicarMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse dividir(br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDividirMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse potencia(br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPotenciaMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse modulo(br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getModuloMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufg.calculadora.OperacaoResponse raizQuadrada(br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRaizQuadradaMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Calculadora.
   */
  public static final class CalculadoraFutureStub
      extends io.grpc.stub.AbstractFutureStub<CalculadoraFutureStub> {
    private CalculadoraFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculadoraFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculadoraFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufg.calculadora.OperacaoResponse> somar(
        br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSomarMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufg.calculadora.OperacaoResponse> subtrair(
        br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubtrairMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufg.calculadora.OperacaoResponse> multiplicar(
        br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMultiplicarMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufg.calculadora.OperacaoResponse> dividir(
        br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDividirMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufg.calculadora.OperacaoResponse> potencia(
        br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPotenciaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufg.calculadora.OperacaoResponse> modulo(
        br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getModuloMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufg.calculadora.OperacaoResponse> raizQuadrada(
        br.ufg.calculadora.OperacaoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRaizQuadradaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SOMAR = 0;
  private static final int METHODID_SUBTRAIR = 1;
  private static final int METHODID_MULTIPLICAR = 2;
  private static final int METHODID_DIVIDIR = 3;
  private static final int METHODID_POTENCIA = 4;
  private static final int METHODID_MODULO = 5;
  private static final int METHODID_RAIZ_QUADRADA = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SOMAR:
          serviceImpl.somar((br.ufg.calculadora.OperacaoRequest) request,
              (io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse>) responseObserver);
          break;
        case METHODID_SUBTRAIR:
          serviceImpl.subtrair((br.ufg.calculadora.OperacaoRequest) request,
              (io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse>) responseObserver);
          break;
        case METHODID_MULTIPLICAR:
          serviceImpl.multiplicar((br.ufg.calculadora.OperacaoRequest) request,
              (io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse>) responseObserver);
          break;
        case METHODID_DIVIDIR:
          serviceImpl.dividir((br.ufg.calculadora.OperacaoRequest) request,
              (io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse>) responseObserver);
          break;
        case METHODID_POTENCIA:
          serviceImpl.potencia((br.ufg.calculadora.OperacaoRequest) request,
              (io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse>) responseObserver);
          break;
        case METHODID_MODULO:
          serviceImpl.modulo((br.ufg.calculadora.OperacaoRequest) request,
              (io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse>) responseObserver);
          break;
        case METHODID_RAIZ_QUADRADA:
          serviceImpl.raizQuadrada((br.ufg.calculadora.OperacaoRequest) request,
              (io.grpc.stub.StreamObserver<br.ufg.calculadora.OperacaoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSomarMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.ufg.calculadora.OperacaoRequest,
              br.ufg.calculadora.OperacaoResponse>(
                service, METHODID_SOMAR)))
        .addMethod(
          getSubtrairMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.ufg.calculadora.OperacaoRequest,
              br.ufg.calculadora.OperacaoResponse>(
                service, METHODID_SUBTRAIR)))
        .addMethod(
          getMultiplicarMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.ufg.calculadora.OperacaoRequest,
              br.ufg.calculadora.OperacaoResponse>(
                service, METHODID_MULTIPLICAR)))
        .addMethod(
          getDividirMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.ufg.calculadora.OperacaoRequest,
              br.ufg.calculadora.OperacaoResponse>(
                service, METHODID_DIVIDIR)))
        .addMethod(
          getPotenciaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.ufg.calculadora.OperacaoRequest,
              br.ufg.calculadora.OperacaoResponse>(
                service, METHODID_POTENCIA)))
        .addMethod(
          getModuloMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.ufg.calculadora.OperacaoRequest,
              br.ufg.calculadora.OperacaoResponse>(
                service, METHODID_MODULO)))
        .addMethod(
          getRaizQuadradaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.ufg.calculadora.OperacaoRequest,
              br.ufg.calculadora.OperacaoResponse>(
                service, METHODID_RAIZ_QUADRADA)))
        .build();
  }

  private static abstract class CalculadoraBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalculadoraBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return br.ufg.calculadora.CalculadoraProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Calculadora");
    }
  }

  private static final class CalculadoraFileDescriptorSupplier
      extends CalculadoraBaseDescriptorSupplier {
    CalculadoraFileDescriptorSupplier() {}
  }

  private static final class CalculadoraMethodDescriptorSupplier
      extends CalculadoraBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CalculadoraMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CalculadoraGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculadoraFileDescriptorSupplier())
              .addMethod(getSomarMethod())
              .addMethod(getSubtrairMethod())
              .addMethod(getMultiplicarMethod())
              .addMethod(getDividirMethod())
              .addMethod(getPotenciaMethod())
              .addMethod(getModuloMethod())
              .addMethod(getRaizQuadradaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
