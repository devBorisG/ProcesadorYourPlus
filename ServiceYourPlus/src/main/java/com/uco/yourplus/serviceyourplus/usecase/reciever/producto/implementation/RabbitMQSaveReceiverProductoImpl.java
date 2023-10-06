package com.uco.yourplus.serviceyourplus.usecase.reciever.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.producto.RegistrarProducto;
import com.uco.yourplus.serviceyourplus.usecase.reciever.producto.RabbitMQSaveReceiverProducto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSaveReceiverProductoImpl implements RabbitMQSaveReceiverProducto {

    private final RegistrarProducto useCase;

    public RabbitMQSaveReceiverProductoImpl(RegistrarProducto useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = "${yourplus.management.producto.queue.save}")
    @Override
    public void execute(String domain) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        ResponseDomain responseDomain = new ResponseDomain();
        try {
//            useCase.execute(domain);
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Producto registrado con éxito");
        }catch (ServiceCustomException exception){
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()){
                responseDomain.setMessage("Algo salio mal registrando el producto, intenta nuevamente");
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
