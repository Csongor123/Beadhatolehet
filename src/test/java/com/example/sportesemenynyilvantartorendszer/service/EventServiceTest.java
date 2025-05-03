package com.example.sportesemenynyilvantartorendszer.service;

import com.example.sportesemenynyilvantartorendszer.model.Event;
import com.example.sportesemenynyilvantartorendszer.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    private EventRepository eventRepository;
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        eventRepository = mock(EventRepository.class);
        eventService = new EventServiceImpl(eventRepository);
    }

    @Test
    void testFindAll() {
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.findAll();

        assertEquals(2, result.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Event event = new Event();
        event.setId(1L);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Optional<Event> found = eventService.findById(1L);

        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
    }

    @Test
    void testSave() {
        Event event = new Event();
        event.setName("Futóverseny");

        when(eventRepository.save(event)).thenReturn(event);

        Event saved = eventService.save(event);

        assertEquals("Futóverseny", saved.getName());
    }

    @Test
    void testDeleteById() {
        Long eventId = 1L;

        eventService.deleteById(eventId);

        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    void testFindByCategory() {
        String category = "Futás";
        Event event = new Event();
        event.setCategory(category);
        when(eventRepository.findByCategory(category)).thenReturn(List.of(event));

        List<Event> result = eventService.findByCategory(category);

        assertEquals(1, result.size());
        assertEquals("Futás", result.get(0).getCategory());
    }
}
