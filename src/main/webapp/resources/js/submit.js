document.getElementById("myCanvas").addEventListener('click', function (event) {
    const coordsWithOffset = getWithOffset(canvas, event);
    let x_input = document.querySelector('input[name="form:valueOfX"]');
    let y_input = document.querySelector('input[name="form:valueOfY"]');
    // console.log(y_input, x_input);
    x_input.value = (coordsWithOffset.x-canvas.width/2)/100;
    y_input.value = (-coordsWithOffset.y+canvas.height/2)/100;
    // console.log(coordsWithOffset.x, coordsWithOffset.y);
    console.log(x_input.value,y_input.value);
    // document.querySelector('form').submit();

});


function getWithOffset(canvas, event) {
    let rect = canvas.getBoundingClientRect();
    return {
        // 5 = border width
        x: event.clientX - rect.left - 5,
        y: event.clientY - rect.top - 5
    };
}

