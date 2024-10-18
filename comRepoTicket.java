package com.project.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Ticket;

public interface comRepoTicket  extends JpaRepository<Ticket, Integer>  
{


}
