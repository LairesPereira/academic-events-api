package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.DAO.PresenceListDAO;
import com.academicevents.api.DTO.event.DeleteEventDTO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.event.SearchEventDTO;
import com.academicevents.api.customerrors.EventNotExistsError;
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
    public static ResponseEntity<?> saveEvent(EventDTO event) {
        if (EventDAO.searchEventByName(event.getNome())) {
            return new ResponseEntity<>("Erro ao criar o evento. Evento já existente.", HttpStatus.BAD_REQUEST);
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

        return new ResponseEntity<>("Evento criado com sucesso!", HttpStatus.OK);
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
        try {
            Map<String, ArrayList<EventDTO>> responseOK = new HashMap<>();
            responseOK.put("events", EventDAO.listEvents());
            return new ResponseEntity<>(responseOK, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> responseError = new HashMap<>();
            responseError.put("error", e.getMessage());
            return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
