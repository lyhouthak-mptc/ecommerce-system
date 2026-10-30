package co.istad.lyhout.ecommerce.user.biz.mapper;

import co.istad.lyhout.ecommerce.user.dto.UserCreateRequest;
import co.istad.lyhout.ecommerce.user.dto.UserResponse;
import co.istad.lyhout.ecommerce.user.dto.UserUpdateRequest;
import co.istad.lyhout.ecommerce.user.entity.UserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Keycloak holds identity data (username, email, names); the DB holds profile data
    default UserResponse toResponse(UserEntity userEntity, UserRepresentation userRepresentation) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .username(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .familyName(userRepresentation.getFirstName())
                .givenName(userRepresentation.getLastName())
                .address(userEntity.getAddress())
                .birthday(userEntity.getBirthday())
                .gender(userEntity.getGender())
                .mobilePhone(userEntity.getMobilePhone())
                .build();
    }

    // id comes from Keycloak after the user is created there
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserCreateRequest request);

    UserResponse toUserResponse(UserEntity userEntity);

    @Mapping(source = "familyName", target = "firstName")
    @Mapping(source = "givenName", target = "lastName")
    UserRepresentation toRepresentation(UserCreateRequest request);

    // Copy only non-null fields onto the existing entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUserEntity(UserUpdateRequest request, @MappingTarget UserEntity userEntity);

}
