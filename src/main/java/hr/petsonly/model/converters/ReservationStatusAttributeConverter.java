package hr.petsonly.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import hr.petsonly.model.ReservationStatus;

@Converter(autoApply = true)
public class ReservationStatusAttributeConverter implements AttributeConverter<ReservationStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ReservationStatus attribute) {
		switch(attribute){
		case PENDING:
			return 1;
		case ACCEPTED:
			return 2;
		case CONFIRMED:
			return 3;
		case ARCHIVED:
			return 4;
		default :
			return null;
		}
	}

	@Override
	public ReservationStatus convertToEntityAttribute(Integer dbData) {
		switch(dbData){
		case 1:
			return ReservationStatus.PENDING;
		case 2:
			return ReservationStatus.ACCEPTED;
		case 3: 
			return ReservationStatus.CONFIRMED;
		case 4:
			return ReservationStatus.ARCHIVED;
		default :
			return null;
		}
	}

}
