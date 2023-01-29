package com.hack.hackathon.service;


import com.hack.hackathon.dao.AddressRepository;
import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.UserAddress;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
    private AddressRepository addressRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public UserAddress addAddress(UserAddress addressDto) {
		UserAddress save = addressRepository.save(addressDto);
		return save;
	}


	public UserAddress updateAddressById(UserAddress address) throws RecordNotFound {
		boolean userAdd = addressRepository.existsById(address.getId());
		if(!userAdd){
			throw new RecordNotFound("Address Not found By Id "+address.getId());
		}
//		UserAddress userAddress = this.modelMapper.map(address, UserAddress.class);
		UserAddress save = addressRepository.save(address);
		 return save;
	}



	public boolean deleteAddress(String id) throws RecordNotFound {
		boolean byIsActiveAndId = addressRepository.existsById(id);
		if(!byIsActiveAndId){
			throw new RecordNotFound("Address ID Does not exists "+id);
		}
		addressRepository.deleteById(id);
		return true;
	}


	public List<UserAddress> findAllAddressByUserId(String userid) {
		List<UserAddress> byIsActiveAndUserId = addressRepository.findAllAddressByUserId(userid);
		return byIsActiveAndUserId;

	}

	public UserAddress findAddressByAddressId(String id) throws RecordNotFound {
		Optional<UserAddress> byIsActiveAndId = addressRepository.findById(id);
		if(byIsActiveAndId.isEmpty()){
			throw new RecordNotFound("Address Not found By Id "+id);
		}else return byIsActiveAndId.get();

	}
}
