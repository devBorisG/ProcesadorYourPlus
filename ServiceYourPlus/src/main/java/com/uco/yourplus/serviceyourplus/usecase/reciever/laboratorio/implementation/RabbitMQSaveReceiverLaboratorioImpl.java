package com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.RegistrarLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.RabbitMQSaveReceiverLaboratorio;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSaveReceiverLaboratorioImpl implements RabbitMQSaveReceiverLaboratorio {

    private final RegistrarLaboratorio useCase;

    public RabbitMQSaveReceiverLaboratorioImpl(RegistrarLaboratorio useCase) {
        this.useCase = useCase;
    }

    //    @RabbitListener(queues = "${}")
    @Override
    public void execute(LaboratorioDomain domain) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        ResponseDomain responseDomain = new ResponseDomain();
        try {
            useCase.execute(domain);
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Laboratorio registrado con éxito");
        } catch (ServiceCustomException exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()) {
                responseDomain.setMessage("Algo salio mal registrando el laboratorio, intenta nuevamente");
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
