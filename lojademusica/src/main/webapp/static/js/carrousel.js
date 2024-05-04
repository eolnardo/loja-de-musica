const buttonNext = document.querySelector('[data-carrousel-botao-proximo]');
const buttonPrevious = document.querySelector('[data-carrousel-botao-anterior]');
const carrousel = document.querySelector('[data-carrousel]')
const SlidesContainer = document.querySelector('[data-slides-container]')


buttonPrevious.addEventListener('click', anterior);
buttonNext.addEventListener('click', proximo);

const numSlides = SlidesContainer.children.length;




let slideAtual = 0;
function proximo() {
    slideAtual = modulo(slideAtual +1, numSlides);
    carrousel.style.setProperty('--slide-atual', slideAtual);
}

function anterior() {
    slideAtual = modulo(slideAtual - 1, numSlides) ;
    carrousel.style.setProperty('--slide-atual', slideAtual);
}

function modulo (numero, mod){
    let resultado = numero % mod;
    if(resultado <0){
        resultado += mod;
    }
    return resultado;
}