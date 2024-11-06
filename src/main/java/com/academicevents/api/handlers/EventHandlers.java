package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.DAO.PresenceListDAO;
import com.academicevents.api.DTO.event.EventListDTO;
import com.academicevents.api.DTO.event.DeleteEventDTO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.event.SearchEventDTO;
import com.academicevents.api.DTO.event.SubscribeEventDTO;
import com.academicevents.api.customerrors.*;
import com.academicevents.api.models.PresenceList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EventHandlers {
    public static ResponseEntity<?> createEvent(EventDTO event) {
        Map<String, String> response = new HashMap<>();
        if (EventDAO.searchEventByName(event.getNome())) {
            throw new EventAlreadyExistsError("Erro ao criar o evento. Evento já existente.");
        }
        if (!EventDAO.saveEvent(event)) {
            return new ResponseEntity<>("Erro ao criar o evento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String presenceListCode = UUID.randomUUID().toString();
        String eventCode = EventDAO.searchCodeByName(event.getNome());
        PresenceList presenceList = new PresenceList(presenceListCode, eventCode);

        if(!PresenceListDAO.savePresenceList(presenceList)) {
            return new ResponseEntity<>("Erro ao criar a lista de presença.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("success", "Evento criado com sucesso!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<?> deleteEvent(DeleteEventDTO event) {
        if(EventDAO.searchEventByName(event.getNome())) {
             EventDAO.deleteEvent(event.getNome());
            return new ResponseEntity<>("Evento deletado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Evento inexistente.", HttpStatus.BAD_REQUEST);
        }
    }

    public static EventDTO getEventbyName(SearchEventDTO event) {
        if(EventDAO.searchEventByName(event.getNome())) {
            return EventDAO.getEventByName(event.getNome());
        } else {
            throw new EventNotExistsError("Evento inexistente.");
        }
    }

    public static ResponseEntity<?> listEvents() {
        HashMap<String, ArrayList<EventListDTO>> response = new HashMap<>();
        try {
            response.put("events", EventDAO.listEvents());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> responseError = new HashMap<>();
            responseError.put("error", e.getMessage());
            return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static boolean subscribeEvent(SubscribeEventDTO subscription) {
        if (!UserHandlers.checkIfUserExistsByCpf(subscription.getCpfParticipante())) {
            throw new UserNotFoundError("CPF não encontrado");
        }

        if (!UserHandlers.checkIfEmailBelongsToUser(subscription.getCpfParticipante(), subscription.getEmailParticipante())) {
            throw new WrongCredentialsError("Email pertence à outro usuário");
        }

        if (!EventDAO.checkIfEventExistsByName(subscription.getNomeEvento())) {
            throw new EventNotExistsError("Evento inexistente! Verifique o nome do evento e tente novamente.");
        }

        String eventCode = EventDAO.searchCodeByName(subscription.getNomeEvento());
        if (PresenceListDAO.checkIfUserIsSubscribed(eventCode, subscription.getCpfParticipante())) {
            throw new UserAlreadySubscribedError("Participante ja está inscrito.");
        }

        System.out.println(eventCode);
        String lpEventCode = EventDAO.getLpEventCode(eventCode);
        if(EventDAO.subscribeEvent(lpEventCode, eventCode, subscription.getCpfParticipante())) {
            return true;
        }

        return false;
    }
}
