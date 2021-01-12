$(document).ready(function () {
    $('.btn-load-weather').click(function () {
        $.ajax({
            url: '/api/get',
            dataType: 'json',
            method: 'get',
            data: {
                cityName: $('.city-name').val(),
                type: $('.api-type').val(),
            },
            success: function (data) {
                $('.field-name').html(data.name);
                $('.field-temperature').html(data.temperature);
                $('.field-pressure').html(data.pressure);
                $('.field-feels-like').html(data.feelsLike);
                $('.field-description').html(data.weatherDescription);
                $('.field-wind-speed').html(data.windSpeed);
            },
            error: function (err) {
                console.log(err);
            }
        });
    });
});