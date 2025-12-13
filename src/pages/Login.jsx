import { useState } from "react"
import { Link, redirect } from "react-router-dom"
import "../styles/Login.css"
import { useSelector } from "react-redux"

const Login = () => {

    var authtoken = useSelector((state)=>state.token.token)

    const [formdata, setFormdata] = useState({
        email: "",
        password: ""
    })
    
    function handleChange(e) {
        const {name, value} = e.target
        setFormdata((prev)=>({...prev,[name]:value}))
    }

    async function login() {
        const response = await fetch("http://localhost:8080/auth/login",{
            method:"POST",
            body: JSON.stringify(formdata),
            headers:{
                'Content-Type':"application/json"
            }
        })

        if (response.ok) {
            const token = await response.headers.get('authorization');
            authtoken = token
            redirect("/dashboard")
        }
        else{
            alert("Login failed.")
        }
    }

    return (
        <div className='login_box'>
            <h2>Login</h2>
            <label htmlFor="username">Username: </label>
            <input type="text" name="username" id="username" value={formdata.email} onChange={handleChange} autoComplete='false'/> <br />
            <label htmlFor="password">Password: </label>
            <input type="password" name="password" id="password" value={formdata.password} onChange={handleChange} autoComplete='false'/> <br />
            <input type="button" value="Login" />
            <input type="reset" value="Clear" onClick={login} /><br />
            <Link to={"/register"}>Dont't have an account ? Signup Here</Link>
        </div>
    )
}

export default Login