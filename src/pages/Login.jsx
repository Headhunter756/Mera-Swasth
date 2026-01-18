import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import "../styles/Login.css"
import { useSelector, useDispatch } from "react-redux"
import { save } from "../storage/token"

const Login = () => {

    const authtoken = useSelector((state)=>state.token.token)
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [formdata, setFormdata] = useState({
        email: "",
        password: ""
    })
    
    function handleChange(e) {
        const {name, value} = e.target
        setFormdata((prev)=>({...prev,[name]:value}))
    }

    async function login() {
        const query = `
            mutation login($input: CreateUser!) {
                login(input: $input) {
                    token
                    user { userid name email }
                }
            }
        `

        const resp = await fetch("http://localhost:8080/graphql", {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                query,
                variables: { input: { email: formdata.email, password: formdata.password } }
            })
        })

        if (!resp.ok) {
            alert('Network error during login')
            return
        }

        const result = await resp.json()
        if (result.errors) {
            alert('Login failed: ' + (result.errors[0]?.message || 'Unknown error'))
            return
        }

        const token = result.data?.login?.token
        if (token) {
            //dispatch(save(token))
            //navigate('/dashboard')
            alert("token: "+token)
        } else {
            alert('Login failed.')
        }
    }

    function handleClear() {
        setFormdata({ email: "", password: "" })
    }

    return (
        <div className='login_box'>
            <h2>Login</h2>
            <label htmlFor="email">Email: </label>
            <input type="text" name="email" id="email" value={formdata.email} onChange={handleChange} autoComplete="off"/> <br />
            <label htmlFor="password">Password: </label>
            <input type="password" name="password" id="password" value={formdata.password} onChange={handleChange} autoComplete="off"/> <br />
            <input type="button" value="Login" onClick={login} />
            <input type="button" value="Clear" onClick={handleClear} /><br />
            <Link to={"/register"}>Dont't have an account ? Signup Here</Link>
        </div>
    )
}

export default Login