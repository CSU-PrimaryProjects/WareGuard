function getDateFormat(date, format) {
    if (date == null || date == '') {
        return '';
    }
    var dateVal = new Date(date);
    var year = dateVal.getFullYear();
    var month = (dateVal.getMonth() + 1).toString().padStart(2, '0');
    var day = dateVal
        .getDate()
        .toString()
        .padStart(2, '0');
    var hour = dateVal
        .getHours()
        .toString()
        .padStart(2, '0');
    var Mints = dateVal
        .getMinutes()
        .toString()
        .padStart(2, '0');
    var Secs = dateVal
        .getSeconds()
        .toString()
        .padStart(2, '0');
    var timeDate = '';
    if (format === 'yyyy-MM-dd') {
        timeDate = year + '-' + month + '-' + day;
    } else if (format === 'yyyy-MM-dd HH:mm:ss') {
        timeDate = year + '-' + month + '-' + day + ' ' + hour + ':' + Mints + ':' + Secs;
    } else if (format === 'yyyy.MM.dd') {
        timeDate = year + '.' + month + '.' + day;
    } else if (format === 'yyyy.MM') {
        timeDate = year + '.' + month;
    }
    return timeDate;
}