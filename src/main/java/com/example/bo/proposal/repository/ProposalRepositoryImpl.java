package com.example.bo.proposal.repository;

import com.example.bo.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepositoryImpl extends ProposalRepository, JpaRepository<Proposal, Long> {
}
