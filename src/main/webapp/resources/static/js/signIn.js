ㄴ
function signup(){
    const email = document.getElementById("email").value
    const writer = document.getElementById("writer").value
    const password1 = document.getElementById("password1").value
    const password2 = document.getElementById("password2").value

    const location = document.getElementById("location").value

    const genderWoman = document.getElementById("gender__woman").checked
    const genderMan = document.getElementById("gender__man").checked

    let isValid = true
    if(email.includes("@") === false) {
        document.getElementById("error__email").innerText = "이메일이 올바르지 않습니다."
        isValid = false
    } else {
        document.getElementById("error__email").innerText = ""
    }

    if(writer === "") {
        document.getElementById("error__writer").innerText = "이름이 올바르지 않습니다."
        isValid = false
    } else {
        document.getElementById("error__writer").innerText = ""
    }

    if(password === ""){
        document.getElementById("error__password1").innerText = "비밀번호를 입력해 주세요."
        isValid = false
    } else {
        document.getElementById("error__password1").innerText = ""
    }



    // 각 각 조건이 하나라도 안맞으면 isValid가 false이므로 가입승인이 되지 않는다
    if(isValid === true){
        alert("코드캠프 가입을 축하합니다.")
    }
}