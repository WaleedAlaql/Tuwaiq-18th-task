package com.waleed.task18.Service;

import com.waleed.task18.Api.APIException;
import com.waleed.task18.DTO.AddressDTO;
import com.waleed.task18.Entity.Address;
import com.waleed.task18.Entity.Teacher;
import com.waleed.task18.Repository.AddressRepository;
import com.waleed.task18.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacherId());
        if (teacher == null)
            throw new APIException("Teacher not found");

        if (teacher.getAddress() != null)
            throw new APIException("Teacher already has an address");

        Address address = new Address(null, addressDTO.getArea(), addressDTO.getStreet(), addressDTO.getBuildingNumber(), teacher);
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findAddressById(addressDTO.getTeacherId());
        if (address == null)
            throw new APIException("Address not found");

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());

        addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        Address address = addressRepository.findAddressById(id);
        if (address == null)
            throw new APIException("Address not found");
        addressRepository.delete(address);
    }
}