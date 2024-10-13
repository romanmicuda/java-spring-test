package sk.uteg.springdatatest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.uteg.springdatatest.api.model.CampaignSummary;
import sk.uteg.springdatatest.exception.IllegalOperationException;
import sk.uteg.springdatatest.exception.NotFoundException;
import sk.uteg.springdatatest.service.ICampaignService;

import java.util.UUID;

@RestController
@RequestMapping("/campaign")
@RequiredArgsConstructor
public class CampaignController {

    private final ICampaignService campaignService;

    @GetMapping("/summary/{uuid}")
    public ResponseEntity<CampaignSummary> getCampaignSummary(@PathVariable UUID uuid) 
            throws NotFoundException, IllegalOperationException {
        return ResponseEntity.ok(campaignService.getCampaignSummary(uuid));

    }

}
