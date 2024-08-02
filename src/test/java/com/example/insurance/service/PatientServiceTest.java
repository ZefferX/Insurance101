package com.example.insurance.service;

import com.example.insurance.dto.NewInsuranceRequest;
import com.example.insurance.dto.NewPatientRequest;
import com.example.insurance.model.Insurance;
import com.example.insurance.model.MedicineTicket;
import com.example.insurance.model.Patient;
import com.example.insurance.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock private PatientRepository patientRepository;
    @InjectMocks private PatientService serviceUnderTest;
    @Mock private InsuranceService insuranceService;
    @Test
    void shouldGetPacientsHappyCase() {
        //given
        List<Patient> patientsList = Collections.singletonList(mock(Patient.class));
        //when
        when(patientRepository.findAll()).thenReturn(patientsList);
        List<Patient> response = serviceUnderTest.getPacients();
        //then
        verify(patientRepository, atMostOnce()).findAll();
        assertThat(response).isEqualTo(patientsList);

    }

    @Test
    void shouldThrowExceptionOnGetPacients() {
        //given
        List<Patient> patientsList = Collections.singletonList(mock(Patient.class));
        //when
        when(patientRepository.findAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, ()-> serviceUnderTest.getPacients());
        //then
        verify(patientRepository, atMostOnce()).findAll();

    }


    @Test
    void shouldGetPacientByIdHappyCase() {
        //given
        Patient patient = mock(Patient.class);
        //when
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patient));
        Patient response = serviceUnderTest.getPacientById(1);
        //then
        assertThat(response).isEqualTo(patient);
        verify(patientRepository, atMostOnce()).findById(1);

    }

    @Test
    void shouldThrowOnGetPacientById() {
        //given
        //when
        when(patientRepository.findById(1)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, ()-> serviceUnderTest.getPacientById(1));
        //then

        verify(patientRepository, atMostOnce()).findById(1);

    }

    @Test
    void shouldAddPacientHappyCase() {
        //given
        Patient patient = mock(Patient.class);
        NewPatientRequest dtoPatient = new NewPatientRequest(null,null,null);
        Insurance insurance = mock(Insurance.class);

        //when
        when(insuranceService.findInsuranceById(dtoPatient.insuranceId())).thenReturn(insurance);
        when(patientRepository.save(any())).thenReturn(patient);
        serviceUnderTest.addPacient(dtoPatient);
        //then
        verify(insuranceService, atMostOnce()).findInsuranceById(dtoPatient.insuranceId());
        verify(patientRepository, atMostOnce()).save(patient);


    }

    @Test
    void shouldAddPacientHappyCaseDoesNotThrow() {
        //given
        Patient patient = mock(Patient.class);
        NewPatientRequest dtoPatient = new NewPatientRequest(null,null,null);
        Insurance insurance = mock(Insurance.class);

        //when
        when(insuranceService.findInsuranceById(dtoPatient.insuranceId())).thenReturn(insurance);
        when(patientRepository.save(any())).thenReturn(patient);

        //then
        assertDoesNotThrow(()-> serviceUnderTest.addPacient(dtoPatient));
        verify(insuranceService, atMostOnce()).findInsuranceById(dtoPatient.insuranceId());
        verify(patientRepository, atMostOnce()).save(patient);


    }

    @Test
    void shouldThrowExceptionOnAddPacient() {
        //given
        NewPatientRequest newPatientRequest = new NewPatientRequest("Carlos", 23, 1);
        Insurance insurance = mock(Insurance.class);
        //when
        when(insuranceService.findInsuranceById(1)).thenReturn(insurance);
        doThrow(RuntimeException.class).when(patientRepository).save(Mockito.any(Patient.class));
        //then
        assertThrows(RuntimeException.class, ()-> serviceUnderTest.addPacient(newPatientRequest));
        verify(patientRepository, atMostOnce()).save(Mockito.any(Patient.class));


    }

    @Test
    void ShouldDeletePacientHappyCase() {
        //given
        Integer patientToDelete = 1;
        Patient patient = mock(Patient.class);
        //when
        when(patientRepository.findById(patientToDelete)).thenReturn(Optional.ofNullable(patient));
        String deletedPatient = serviceUnderTest.deletePacient(patientToDelete);
        //then
        assertThat(deletedPatient).isEqualTo("Patient deleted successfully");

    }

    @Test
    void ShouldThrowExceptionOnDeletePacient() {
        //given
        Integer patientToDelete = 1;

        //when
        when(patientRepository.findById(patientToDelete)).thenReturn(Optional.empty());
        //then
        String result = serviceUnderTest.deletePacient(patientToDelete);
        assertEquals("Patient with id " + patientToDelete + " not found", result);
        verify(patientRepository, never()).deleteById(patientToDelete);
    }

    @Test
    void shouldUpdatePacientHappyCase() {


    }

    @Test
    void updatePatientToInternalUse() {
    }
}