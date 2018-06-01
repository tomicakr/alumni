// package hr.alumni.model.converter;

// import javax.persistence.AttributeConverter;
// import javax.persistence.Converter;

// import hr.alumni.model.PostType;

// @Converter(autoApply = true)
// public class PostTypeConverter implements AttributeConverter<PostType, Integer> {

// 	@Override
// 	public Integer convertToDatabaseColumn(PostType attribute) {
// 		switch(attribute){
// 		case EVENT:
// 			return 1;
// 		case LECTURE:
// 			return 2;
// 		case INFO:
// 			return 3;
// 		default :
// 			return null;
// 		}
// 	}

// 	@Override
// 	public PostType convertToEntityAttribute(Integer dbData) {
// 		switch(dbData){
// 		case 1:
// 			return PostType.EVENT;
// 		case 2:
// 			return PostType.LECTURE;
// 		case 3: 
// 			return PostType.INFO;
// 		default :
// 			return null;
// 		}
// 	}

// }
