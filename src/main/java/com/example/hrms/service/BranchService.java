package com.example.hrms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hrms.entity.Branch;

@Service
public class BranchService {
	private List<Branch> branches=new ArrayList<>();
	
	public BranchService() {
		prefill();
	}
	
	public void prefill()
	{
		branches.add(new Branch("B001", "New branch", "Kolkata"));
		branches.add(new Branch("B002", "Old branch", "Mumbai"));
		branches.add(new Branch("B003", "College branch", "Coimbatore"));
		branches.add(new Branch("B004", "Temple branch", "Madurai"));
		branches.add(new Branch("B005", "Main branch", "Chennai"));
		branches.add(new Branch("B006", "OMR branch", "Chennai"));
		branches.add(new Branch("B007", "Marine drive branch", "Mumbai"));
		branches.add(new Branch("B008", "NSC branch", "Kolkata"));
		branches.add(new Branch("B009", "LB road branch", "Chennai"));
		branches.add(new Branch("B010", "DB road branch", "Coimbatore"));
	}
	
	public void create(Branch branch)
	{
		branches.add(branch);
	}
	public List<Branch> read()
	{
		return branches;
	}
	public Branch read(String bid)
	{
		for(Branch b:branches)
		{
			System.out.println("Comparing "+b.getBid()+" with "+bid);
			if(b.getBid().equals(bid))
			{
				System.out.println("Found. returning");
				return b;
			}
		}
		return null;
	}
	public void update(Branch branch)
	{
		for(Branch b:branches)
		{
			if(b.getBid().equals(branch.getBid()))
			{
				b.setBname(branch.getBname());
				b.setBcity(branch.getBcity());
			}
		}
	}
	public void delete(String bid)
	{
		for(Branch b:branches)
		{
			if(b.getBid().equals(bid))
			{
				branches.remove(b);			//ConcurrentModificationException
				return;
			}
		}
	}
	
}
