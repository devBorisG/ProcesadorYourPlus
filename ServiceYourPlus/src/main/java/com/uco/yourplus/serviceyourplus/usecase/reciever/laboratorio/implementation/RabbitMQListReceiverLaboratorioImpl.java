package com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.ConsultarLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.RabbitMQListReceiverLaboratorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitMQListReceiverLaboratorioImpl implements RabbitMQListReceiverLaboratorio {

    private final ConsultarLaboratorio useCase;

    public RabbitMQListReceiverLaboratorioImpl(ConsultarLaboratorio useCase) {
        this.useCase = useCase;
    }

    //    @RabbitListener(queues = "${}")
    @Override
    public void execute(LaboratorioDomain domain) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        ResponseDomain responseDomain = new ResponseDomain();
        try {
            useCase.execute(Optional.of(domain));
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Lista de laboratorios");
        } catch (ServiceCustomException exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()) {
                responseDomain.setMessage("Algo salio mal consultando los laboratorios, intenta nuevamente");
            } else {
                responseDomain.setMessage(exception.getMessage());
            }
        } catch (Exception exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrió un error fatal, intentalo en unos minutos");
        } finally {
            //TODO: Agregar el sender de rabbit
        }
    }
}
