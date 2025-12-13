import { Outlet } from 'react-router-dom'
import "../styles/Signup.css"

const Signup = () => {
    return (
        <>
            <div className="signup_box centering">
                <Outlet />
            </div>
        </>
    )
}

export default Signup