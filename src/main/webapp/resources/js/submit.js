document.getElementById("myCanvas").addEventListener('click', function (event) {
    const coordsWithOffset = getWithOffset(canvas, event);
    let x_input = document.getElementById('form:hiddenX');
    let y_input = document.getElementById('form:valueOfY');
    x_input.value = ((coordsWithOffset.x - canvas.width / 2) / 100).toFixed(3);
    y_input.value = ((-coordsWithOffset.y + canvas.height / 2) / 100).toFixed(3);
    document.getElementById('form:valueOfX:0').setAttribute('checked', 'true');
    document.getElementById('form:sendButton').click();
});


function getWithOffset(canvas, event) {
    let rect = canvas.getBoundingClientRect();
    return {
        // 5 = border width
        x: event.clientX - rect.left - 5,
        y: event.clientY - rect.top - 5
    };
}

