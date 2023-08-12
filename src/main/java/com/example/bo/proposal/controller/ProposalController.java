package com.example.bo.proposal.controller;

import com.example.bo.member.entity.AuthUser;
import com.example.bo.proposal.dto.ProposalDto;
import com.example.bo.proposal.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposal")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService proposalService;
    @PostMapping("")
    public ResponseEntity<Void> submitProposal(@AuthenticationPrincipal AuthUser authUser, @RequestBody ProposalDto proposalDto) {
        proposalDto.setMemId(authUser.getMemId());
        proposalService.submit(proposalDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
