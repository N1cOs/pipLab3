const canvas = document.getElementById('myCanvas');
const context = canvas.getContext('2d');
const width = canvas.width;
const height = canvas.height;

class Point{
    constructor(x, y){
        this.x = x;
        this.y = y;
    }

    multiply(value){
        this.x *= value;
        this.y *= value;
    }
}

class Parabola{
    constructor(leftPoint, rightPoint, vertex){
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.vertex = vertex;
    }

    getCoefficients(scale){
        this.leftPoint.multiply(scale);
        this.rightPoint.multiply(scale);
        this.vertex.multiply(scale);

        let x1 = this.leftPoint.x;
        let y1 = this.leftPoint.y;
        let x2 = this.rightPoint.x;
        let y2 = this.rightPoint.y;
        let x3 = this.vertex.x;
        let y3 = this.vertex.y;

        let a = (y3 - ((x3 * (y2 - y1) + x2 * y1 - x1 * y2) / (x2 - x1))) / (x3 * (x3 - x1 -x2) + x1 * x2);
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
    context.beginPath();
    context.moveTo(width / 2, height / 2 - radius * scale * 0.3);
    context.lineTo(width / 2 + scale * radius * 0.1, height / 2 - radius * scale * 0.3);
    context.lineTo(width / 2 + scale * radius * 0.2, height / 2 - radius * scale * 0.4);
    context.lineTo(width / 2 + scale * radius * 0.25, height / 2 - radius * scale * 0.2);

    let {a:a1, b:b1, c:c1} = firstParabola.getCoefficients(radius);
    for(let i = 1; i <= 25 * radius; i++){
        let x = 0.25 * radius + i * 0.01;
        let y = (a1 * x  * x + b1 * x + c1);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    for(let i = 1; i <= 50 * radius; i++){
        let x = 0.5 * radius + i * 0.01;
        let y = firstEllipse(x, radius);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    for(let i = 0; i<= 42 * radius; i++){
        let x = 1 * radius - i * 0.01;
        let y = secondEllipse(x, radius);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    let{a:a2, b:b2, c:c2} = secondParabola.getCoefficients(radius);
    for(let i = 1; i <= 26 * radius; i++){
        let x = 0.58 * radius - i * 0.01;
        let y = (a2 * x  * x + b2 * x + c2);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }

    let{a:a3, b:b3, c:c3} = thirdParabola.getCoefficients(radius);
    for(let i = 1; i <= 26 * radius; i++){
        let x = 0.26 * radius - i * 0.01;
        let y = (a3 * x  * x + b3 * x + c3);
        context.lineTo(width / 2 + scale * x, height / 2 - scale * y);
    }
    context.stroke();

}

function getWithOffset(canvas, event) {
    let rect = canvas.getBoundingClientRect();
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    };
}

draw(2.5);