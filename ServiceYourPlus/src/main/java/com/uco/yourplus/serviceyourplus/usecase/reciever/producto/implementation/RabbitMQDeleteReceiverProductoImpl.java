package com.uco.yourplus.serviceyourplus.usecase.reciever.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.producto.EliminarProducto;
import com.uco.yourplus.serviceyourplus.usecase.reciever.producto.RabbitMQDeleteReceiverProducto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQDeleteReceiverProductoImpl implements RabbitMQDeleteReceiverProducto {

    private final EliminarProducto useCase;
    private final MapperJsonObject mapperJsonObject;

    public RabbitMQDeleteReceiverProductoImpl(EliminarProducto useCase, MapperJsonObject mapperJsonObject) {
        this.useCase = useCase;
        this.mapperJsonObject = mapperJsonObject;
    }

    @RabbitListener(queues = "${yourplus.management.producto.queue.delete}")
    @Override
    public void execute(String message) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        final ResponseDomain<ProductoDomain> responseDomain = new ResponseDomain();
        try {
            ProductoDomain domain = mapperJsonObject.execute(message, ProductoDomain.class).get();
            useCase.execute(domain);
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Producto eliminado con éxito");
        }catch (ServiceCustomException exception){
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()){
                responseDomain.setMessage("Algo salio mal eliminando el producto, intenta nuevamente");
            }else {
                responseDomain.setMessage(exception.getMessage());
            }
        }catch (Exception exception){
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrió un error fatal, intentalo en unos minutos");
        }finally {
            //TODO: Agregar el sender de rabbit
        }
    }
}
