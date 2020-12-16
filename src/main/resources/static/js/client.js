async function client(url, method, data) {
    const res = await fetch(url, {
        method,
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(data)
    })
    const resData = await res.json()
    if (res.ok) {
        return resData
    } else {
        throw resData
    }
}

const getStudent = async (id) =>
    await client(`students/${id}`)

const getAllStudents = async () => {
    const res = await fetch('/students')
    const resData = await res.json()
    if (res.ok) {
        if (resData._embedded) {
            return resData._embedded.studentList
        }
    } else {
        throw resData
    }
}

const saveStudent = async (student) =>
    await client('students', 'PUT', {...student})

const updateStudent = async (student) =>
    await client('students', 'POST', {...student})

const deleteStudent = async (id) =>
    await client(`students/${id}`, 'DELETE')


