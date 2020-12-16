const formAdd = document.getElementById('formAdd')
const btnAdd = document.getElementById('btnAdd')
const divAdd = document.getElementById('divAdd')

btnAdd.addEventListener('click', async () => {
    const student = {
        firstName: formAdd['firstName'].value.trim(),
        secondName: formAdd['secondName'].value.trim(),
        email: formAdd['email'].value.trim(),
        phoneNumber: formAdd['phoneNumber'].value.trim(),
        address: formAdd['address'].value.trim(),
        entryPoints: formAdd['entryPoints'].value.trim(),
    }

    for (const prop in student) {
        if (!student[prop]) {
            return
        }
    }

    try {
        await saveStudent(student)
        divAdd.innerText = "Added"
    } catch (e) {
        divAdd.innerText = e.message
    }
})


const txtGetOne = document.getElementById('txtGetOne')
const btnGetOne = document.getElementById('btnGetOne')
const divGetOne = document.getElementById('divGetOne')

btnGetOne.addEventListener('click', async () => {
    const admissionNumber = txtGetOne.value.trim()
    if (admissionNumber) {
        try {
            const student = await getStudent(admissionNumber)
            let readableStudent = ''
            for (const prop in student) {
                if (student.hasOwnProperty(prop) && !prop.startsWith('_'))
                    readableStudent += `${prop}: ${student[prop]}\n`
            }
            divGetOne.innerText = readableStudent
        } catch (e) {
            divGetOne.innerText = e.message
        }
    }
})


const btnGetAll = document.getElementById('btnGetAll')
const divGetAll = document.getElementById('divGetAll')

btnGetAll.addEventListener('click', async () => {
    const students = await getAllStudents()
    if (students) {
        let readableStudents = ''
        for (const student of students) {
            for (const prop in student) {
                if (student.hasOwnProperty(prop) && !prop.startsWith('_')) {
                    readableStudents += `${prop}: ${student[prop]}, `
                }
            }
            readableStudents += '\n'
        }
        divGetAll.innerText = readableStudents
    } else {
        divGetAll.innerText = 'No records'
    }
})


const txtDelete = document.getElementById('txtDelete')
const btnDelete = document.getElementById('btnDelete')
const divDelete = document.getElementById('divDelete')

btnDelete.addEventListener('click', async () => {
    const admissionNumber = txtDelete.value.trim()
    if (admissionNumber) {
        try {
            await deleteStudent(admissionNumber)
            divDelete.innerText = 'Deleted'
        } catch (e) {
            divDelete.innerText = 'Error'
        }
    }
})



















/*const student = {
    firstName: "John",
    secondName: "Gachihi",
    email: "johngachihi3@gmail.com",
    phoneNumber: '123456',
    address: 'qwerty',
    entryPoints: 123
}

async function _() {
    const studentData = await saveStudent(student)
    console.log(await getStudent(studentData.id))
    console.log(await updateStudent({...studentData, firstName: 'Johnte'}))
    console.log(await getAllStudents())
    console.log(await deleteStudent(studentData.id))
    console.log(await getAllStudents())
}*/
