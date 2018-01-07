package hr.petsonly.model.converters;

import java.time.Duration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DurationAttributeConverter implements AttributeConverter<Duration, String>{

	@Override
	public String convertToDatabaseColumn(Duration duration) {
		return duration == null ? null : String.format("%2d:%2d", duration.getSeconds() / 3600, (duration.getSeconds() % 3600) / 60);
	}

	@Override
	public Duration convertToEntityAttribute(String dbData) {
		if(dbData == null){
			return null;
		}
		
		String[] parts = dbData.split(":");
		Integer hours = Integer.parseInt(parts[0].trim());
		Integer minutes = Integer.parseInt(parts[1].trim());
		return Duration.ofMinutes(hours * 60 + minutes);
	}
}
