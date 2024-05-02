package com.example.otp.otp;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OtpRepository {

    @Autowired
    private EntityManager entityManager;


    public OtpModel getOtpById(String id) {
        return entityManager.find(OtpModel.class, id);
    }

    @Transactional
        public String createOtp(OtpModel otpModel) {
        entityManager.persist(otpModel);
        entityManager.flush();
        return otpModel.getId();
    }



}
