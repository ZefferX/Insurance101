package com.example.insurance.service;

import com.example.insurance.model.MedicineTicket;
import com.example.insurance.repository.MedicineRepository;
import com.example.insurance.repository.MedicineTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicineTicketServiceTest {

    @Mock private MedicineTicketRepository medicineTicketRepository;

    @InjectMocks private MedicineTicketService serviceUnderTest;






    @Test
    void shouldCreateNewMedicineTicketHappyCase() {

        //Me parece que este test no es correcto, tiene datos repetidos al no tener un DTO el metodo
        //Por lo tanto debe estar comparando el mismo dato, con el mismo dato, o eso creo.
            //given
            MedicineTicket medicineTicket = mock(MedicineTicket.class);
            //when
            when(medicineTicketRepository.save(medicineTicket)).thenReturn(medicineTicket);
            MedicineTicket response = serviceUnderTest.createNewMedicineTicket(medicineTicket);
            //then
            assertThat(response).isEqualTo(medicineTicket);
            verify(medicineTicketRepository, atMostOnce()).save(medicineTicket);



    }

    @Test
    void shouldThrowExceptionOnCreateNewMedicineTicket() {
        //given
        MedicineTicket medicineTicket = mock(MedicineTicket.class);
        //when
        when(medicineTicketRepository.save(medicineTicket)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, ()-> serviceUnderTest.createNewMedicineTicket(medicineTicket));
        //then
        verify(medicineTicketRepository, atMostOnce()).save(medicineTicket);

    }

    @Test
    void shouldGetAllMedicineTicketsHappyCase() {
        //given
        List<MedicineTicket> medicineTicketList = Collections.singletonList(mock(MedicineTicket.class));
        //when

        when(medicineTicketRepository.findAll()).thenReturn(medicineTicketList);
        List<MedicineTicket> response = serviceUnderTest.getAllMedicineTickets();
        //then
        verify(medicineTicketRepository, atMostOnce()).findAll();
        assertThat(response).isEqualTo(medicineTicketList);

    }

    @Test
    void shouldThrowExceptionOnGetAllMedicines() {
        //given
        List<MedicineTicket> medicineTicketList = Collections.singletonList(mock(MedicineTicket.class));
        //when

        when(medicineTicketRepository.findAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, ()-> serviceUnderTest.getAllMedicineTickets());

        //then
        verify(medicineTicketRepository, atMostOnce()).findAll();
    }

    @Test
    void shouldGetMedicineTicketByIdHappyCase() {
        //given
        MedicineTicket medicineTicket = mock(MedicineTicket.class);
        //when
        when(medicineTicketRepository.findById(1)).thenReturn(Optional.ofNullable(medicineTicket));
        MedicineTicket response = serviceUnderTest.getMedicineTicketById(1);
        //then
        verify(medicineTicketRepository, atMostOnce()).findById(1);
        assertThat(response).isEqualTo(medicineTicket);
    }

    @Test
    void shouldThrowExceptionOnGetMedicineTicketById() {
        //given
        //when
        when(medicineTicketRepository.findById(1)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, ()-> serviceUnderTest.getMedicineTicketById(1));
        //then
        verify(medicineTicketRepository, atMostOnce()).findById(1);

    }




}