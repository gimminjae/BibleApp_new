package com.example.bo.proposal.service;

import com.example.bo.proposal.dto.ProposalDto;
import com.example.bo.proposal.entity.Proposal;
import com.example.bo.proposal.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;
    @Override
    public void submit(ProposalDto proposalDto) {
        Proposal proposal = Proposal.from(proposalDto);
        proposalRepository.save(proposal);
    }
}
