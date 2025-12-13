import { useSelector } from "react-redux"
import { NavLink } from "react-router-dom"
import '../styles/Navbar.css'
import logo from '../assets/logo.jpeg'

const Navbar = () => {

    const user = useSelector((state) => state.user.profile)

    return (
        <nav>
            <h2 className="logo">
                <NavLink to={"/"}><img src={logo} alt="logo" className="logo_img"/></NavLink>
            </h2>
            <ul>
                {user &&
                    <>
                        <li><NavLink to={"/dashboard"}>Dashboard</NavLink></li>
                        <li><NavLink to={"/premuim"}>Go Premium</NavLink></li>
                        <li><NavLink to={"/medicine"}>Medicines</NavLink></li>
                        <li><NavLink to={"/history"}>Medical<br />History</NavLink></li>
                    </>
                }
                <li className="emergancy"><NavLink to={"/emergency"}>Emergency</NavLink></li>
            </ul>
            <h2>
                {
                    !user ?
                        <NavLink to={"/login"} className="login_button">Login/Register</NavLink> :
                        <NavLink to={"/profile"} className={"profile_button"}>user.pic</NavLink>
                }
            </h2>
        </nav>
    )
}

export default Navbar