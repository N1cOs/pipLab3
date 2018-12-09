const canvas = document.getElementById('myCanvas');
const context = canvas.getContext('2d');
const width = canvas.width;
const height = canvas.height;

//Resize logo when radius change

const radiusInput = document.querySelector('[id *= "valueOfR"]');
const observer = new MutationObserver(function (mutation) {
    if (radiusInput.value >= 1) {
        context.clearRect(0, 0, width, height);
        draw(radiusInput.value);
        historyDots();
    }
});
observer.observe(radiusInput, {attributes: true});

class Point {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
}

class Parabola {
    constructor(leftPoint, rightPoint, vertex) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.vertex = vertex;
    }

    getCoefficients(scale) {
        let x1 = this.leftPoint.x * scale;
        let y1 = this.leftPoint.y * scale;
        let x2 = this.rightPoint.x * scale;
        let y2 = this.rightPoint.y * scale;
        let x3 = this.vertex.x * scale;
        let y3 = this.vertex.y * scale;

        let a = (y3 - ((x3 * (y2 - y1) + x2 * y1 - x1 * y2) / (x2 - x1))) / (x3 * (x3 - x1 - x2) + x1 * x2);
        let b = (y2 - y1) / (x2 - x1) - a * (x1 + x2);
        let c = (x2 * y1 - x1 * y2) / (x2 - x1) + a * x1 * x2;

        return {a, b, c};
    }
}

function firstEllipse(x, scale) {
    return Math.sqrt((1 - ((x * x) / (scale * scale))) * 0.182 * scale * scale);
}

function secondEllipse(x, scale) {
    return -Math.sqrt((1 - ((x * x) / (scale * scale))) * 0.185 * scale * scale);
}

const scale = 100;
const firstParabola = new Parabola(new Point(0.25, 0.2), new Point(0.5, 0.37),
    new Point(0.35, 0.064));
const secondParabola = new Parabola(new Point(0.26, -0.3476), new Point(0.58, -0.3511),
    new Point(0.4191, -0.2));
const thirdParabola = new Parabola(new Point(0, -0.4482), new Point(0.26, -0.3476),
    new Point(0.1522, -0.2445));

function draw(radius) {

    // --------draw a lines-------
    context.beginPath();
    context.moveTo(0, height / 2);
    context.lineTo(width, height / 2);
    context.strokeStyle = '#000';
    context.stroke();
    context.beginPath();
    context.moveTo(width / 2, 0);
    context.lineTo(width / 2, height);
    context.stroke();

    let {a: a1, b: b1, c: c1} = firstParabola.getCoefficients(radius);
    let {a: a2, b: b2, c: c2} = secondParabola.getCoefficients(radius);
    let {a: a3, b: b3, c: c3} = thirdParabola.getCoefficients(radius);
    context.beginPath();

    // ----------------Right half----------------

    context.moveTo(width / 2, height / 2 - radius * scale * 0.3);
    context.lineTo(width / 2 + scale * radius * 0.1, height / 2 - radius * scale * 0.3);
    context.lineTo(width / 2 + scale * radius * 0.2, height / 2 - radius * scale * 0.4);
    context.lineTo(width / 2 + scale * radius * 0.25, height / 2 - radius * scale * 0.2);

    for (let i = 1; i <= 25 * radius; i++) {
        let x = 0.25 * radius + i * 0.01;
        let y = (a1 * x * x + b1 * x + c1);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    for (let i = 1; i <= 50 * radius; i++) {
        let x = 0.5 * radius + i * 0.01;
        let y = firstEllipse(x, radius);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    for (let i = 0; i <= 42 * radius; i++) {
        let x = 1 * radius - i * 0.01;
        let y = secondEllipse(x, radius);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    for (let i = 1; i <= 26 * radius; i++) {
        let x = 0.58 * radius - i * 0.01;
        let y = (a2 * x * x + b2 * x + c2);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    for (let i = 1; i <= 26 * radius; i++) {
        let x = 0.26 * radius - i * 0.01;
        let y = (a3 * x * x + b3 * x + c3);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    // ----------------Left half----------------

    context.moveTo(width / 2, height / 2 - radius * scale * 0.3);
    context.lineTo(width / 2 - scale * radius * 0.1, height / 2 - radius * scale * 0.3);
    context.lineTo(width / 2 - scale * radius * 0.2, height / 2 - radius * scale * 0.4);
    context.lineTo(width / 2 - scale * radius * 0.25, height / 2 - radius * scale * 0.2);

    for (let i = 1; i <= 25 * radius; i++) {
        let x = 0.25 * radius + i * 0.01;
        let y = a1 * x * x + b1 * x + c1;
        context.lineTo(width / 2 - scale * x, height / 2 - scale * y);
    }

    for (let i = 1; i <= 50 * radius; i++) {
        let x = 0.5 * radius + i * 0.01;
        let y = firstEllipse(x, radius);
        context.lineTo(width / 2 - scale * x, height / 2 - scale * y);
    }

    for (let i = 0; i <= 42 * radius; i++) {
        let x = 1 * radius - i * 0.01;
        let y = secondEllipse(x, radius);
        context.lineTo(width / 2 - scale * x, height / 2 - scale * y);
    }

    for (let i = 1; i <= 26 * radius; i++) {
        let x = 0.58 * radius - i * 0.01;
        let y = a2 * x * x + b2 * x + c2;
        context.lineTo(width / 2 - scale * x, height / 2 - scale * y);
    }

    for (let i = 1; i <= Math.round(26 * radius); i++) {
        let x = 0.26 * radius - i * 0.01;
        let y = a3 * x * x + b3 * x + c3;
        context.lineTo(width / 2 - scale * x, height / 2 - scale * y);
    }

    context.fillStyle = "#000";
    context.fill();
}

draw(1);

function historyDots() {
    const xCoordinates = document.querySelectorAll('td.x_coord');
    const yCoordinates = document.querySelectorAll('td.y_coord');
    const statuses = document.querySelectorAll('td.result');

    for (let i = 0; i < xCoordinates.length; i++) {
        context.beginPath();
        let valueOfX = parseFloat(xCoordinates[i].innerText);
        let valueOfY = parseFloat(yCoordinates[i].innerText);
        context.arc(valueOfX * scale + width / 2, height / 2 - valueOfY * scale, 2, 0, Math.PI * 2);
        if (statuses[i].textContent == 0) {
            context.fillStyle = '#ed1c24';
            statuses[i].textContent = 'нет попадания';
        } else {
            context.fillStyle = '#04f';
            statuses[i].textContent = 'попадание!';
        }
        context.fill();

    }
}
historyDots();
