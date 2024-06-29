package com.example.companyms.messaging;

import com.example.companyms.companies.CompanyService;
import com.example.companyms.companies.dtoReview;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }


    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(dtoReview r) {
        companyService.updateCompanyByRating(r);
    }
}
