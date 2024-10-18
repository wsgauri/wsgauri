package com.project.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Booking;

public interface comRepoBook  extends JpaRepository<Booking, Integer> 
{
		
}
