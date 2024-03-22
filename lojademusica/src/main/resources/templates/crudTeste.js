document.querySelector("#salvar").addEventListener("click", cadastrar)

let tarefas = []

window.addEventListener("load", () => {
    tarefas = JSON.parse(localStorage.getItem("tarefas")) || []
    atualizar()
})

function atualizar() {
    localStorage.setItem("tarefas", JSON.stringify(tarefas))
    document.querySelector("#tarefas").innerHTML = ""
    tarefas.forEach(tarefa =>
        document.querySelector("#tarefas").innerHTML += criarCard(tarefa))

    //const total = tarefas.reduce((acc, tarefa)=> acc += Number(tarefa.pontos),0)
    const pontosObtidos = tarefas.filter(tarefa=> tarefa.concluida).reduce((acc,tarefa) => acc+= Number(tarefa.pontos),0)
    document.querySelector("#pontuacao").innerHTML = pontosObtidos +" R$"
}
function filtrar(lista){
    document.querySelector("#tarefas").innerHTML = ""
    lista.forEach((tarefa) =>document.querySelector("#tarefas").innerHTML += criarCard(tarefa))
}

function cadastrar() {
    const titulo = document.querySelector("#titulo").value
    const pontos = document.querySelector("#pontos").value
    const categoria = document.querySelector("#categoria").value
    const imagem = document.querySelector("#images").value
    const modal = bootstrap.Modal.getInstance(document.querySelector("#exampleModal"))

    const tarefa = { //JSON Java Script Object Notation
        id: Date.now(),
        titulo,
        imagem,
        pontos : Number(pontos),
        categoria,
        concluida: false
    }

    if (!isValid(tarefa.titulo, document.querySelector("#titulo"))) return
    if (!isValid(tarefa.pontos, document.querySelector("#pontos"))) return

    tarefas.push(tarefa)


    atualizar()
    modal.hide()
}

function isValid(valor, campo) {
    if (valor.length == 0) {
        campo.classList.add("is-invalid")
        campo.classList.remove("is-valid")
        return false
    } else {
        campo.classList.add("is-valid")
        campo.classList.remove("is-invalid")
        return true
    }

}

function apagar(id) {
    tarefas = tarefas.filter(tarefa => tarefa.id !== id)
    atualizar()
}

function concluir(concluida){
    let tarefaEncontrada = tarefas.find(tarefa=>tarefa.concluida==concluida)
    tarefaEncontrada.concluida = true
    atualizar()
}

function criarCard(tarefa) {
    let disabled = tarefa.concluida ? "disabled" : ""      //test ? valor_se_true : valor_seFalse
    const card = `
    <div class="card" style="width: 18rem;">
    <div class="card-header">
        ${tarefa.titulo}
    </div>
    <img src="${tarefa.imagem}" class="card-img-top" alt="...">
    <div class="card-body">
        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        <p class="card-text">${tarefa.categoria}</p>
        <span class="badge text-bg-warning">${tarefa.pontos}R$</span>
    </div>
    <div class="card-footer">
        <a href="#" onClick="concluir(${tarefa.concluida})" class="btn btn-success ${disabled}" title="Adicionar ao carrinho">
            <i class="bi bi-bag"></i>
        </a>
        <a href="#" onClick="apagar(${tarefa.id})" class="btn btn-danger" title="Remover do carrinho">
            <i class="bi bi-trash3"></i>
        </a>
    </div> <!-- card footer -->
</div>
    `
    return card
}