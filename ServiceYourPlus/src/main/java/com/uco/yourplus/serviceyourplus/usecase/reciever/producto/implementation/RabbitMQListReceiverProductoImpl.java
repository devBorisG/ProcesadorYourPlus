package com.uco.yourplus.serviceyourplus.usecase.reciever.producto.implementation;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.producto.ConsultarProducto;
import com.uco.yourplus.serviceyourplus.usecase.reciever.producto.RabbitMQListReceiverProducto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListReceiverProductoImpl implements RabbitMQListReceiverProducto {

    private final ConsultarProducto useCase;
   // private final RabbitTemplate rabbitTemplate; // Inyecta una instancia de RabbitTemplate

    public RabbitMQListReceiverProductoImpl(ConsultarProducto useCase/*RabbitTemplate rabbitTemplate*/) {
        this.useCase = useCase;
       // this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "${yourplus.management.producto.queue.list}")
    @Override
    public void execute(String message) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        final ResponseDomain<ProductoDomain> responseDomain = new ResponseDomain<>();
        try {
            ProductoDomain domain = (ProductoDomain) useCase.execute(message);

            //Cargar la relación de Laboratorio y Categoría directamente desde el objeto ProductoDomain
            LaboratorioDTO laboratorio = domain.getLaboratorio();
            CategoriaDTO categoria = domain.getCategoria();

            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Producto(s) consultados con éxito");

            // Enviar el objeto ProductoDomain a una cola de RabbitMQ
           // rabbitTemplate.convertAndSend("nombre-de-la-cola-rabbit", domain);
        } catch (ServiceCustomException exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()) {
                responseDomain.setMessage("Algo salió mal consultando los productos, intenta nuevamente");
            } else {
                responseDomain.setMessage(exception.getMessage());
            }
        } catch (Exception exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrió un error fatal, inténtalo en unos minutos");
        }
    }
}


