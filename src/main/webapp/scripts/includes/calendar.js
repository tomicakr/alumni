let twoDigitFormat = (getter) => ('0' + getter()).slice(-2);

let deklinacijeMrtve = {
    'siječanj': 'siječnja',
    'veljača': 'veljače',
    'ožujak': 'ožujka',
    'travanj': 'travnja',
    'svibanj': 'svibnja',
    'lipanj': 'lipaipnja',
    'srpanj': 'srpnja',
    'kolovoz': 'kolovoza',
    'rujan': 'rujna',
    'listopad': 'listopada',
    'studeni': 'studenog',
    'prosinac': 'prosinca'
};

let monthFormat   = (date) => twoDigitFormat(() => date.getMonth() + 1);
let hourFormat    = (date) => twoDigitFormat(date.getHours.bind(date));
let minutesFormat = (date) => twoDigitFormat(date.getMinutes.bind(date));
let dayFormat     = (date) => twoDigitFormat(date.getDate.bind(date));
let timeFormat    = (date) => `${hourFormat(date)}:${minutesFormat(date)}`;

let timeInputConfig = {
    type: 'time',
    ampm: false,
    formatter:{
        time: timeFormat
    }
};
let today = new Date();
let tommorow = new Date();
tommorow.setDate(today.getDate()+1);
let dateInputConfig = {
    ampm: false,
    monthFirst: false,
    minDate: new Date(tommorow.getFullYear(), tommorow.getMonth(), tommorow.getDate()),
    maxDate: new Date(tommorow.getFullYear(), tommorow.getMonth(), tommorow.getDate()+60),
    text: {
        days: ['P', 'U', 'S', 'Č', 'P', 'S', 'N'],
        months: ['siječanj', 'ožujak', 'ožujak', 'travanj', 'svibanj', 'lipanj', 'srpanj', 'kolovoz', 'rujan', 'listopad', 'studeni', 'prosinac'],
        today: 'Danas',
        now: 'Sad'
    },
    formatter: {
        datetime: function(date){
            if (!date) return '';
            return `${dayFormat(date)}.${monthFormat(date)}.${date.getFullYear()}. ${timeFormat(date)}`;
        }
    }
}