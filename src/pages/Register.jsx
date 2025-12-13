import { useRef, useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import '../styles/Register.css'

const Register = () => {

    const [aadhaar, setAadhaar] = useState("");
    const [abha, setAbha] = useState("");
    const [patient, setPatient] = useState(true)
    const [category, setCategory] = useState("Doctor")

    const addhaarChange = (e) => {
        const value = e.target.value;
        if (/^\d*$/.test(value) && value.length <= 12) {
            setAadhaar(value);
        };
    }
    const abhaChange = (e) => {
        const value = e.target.value;
        if (/^\d*$/.test(value) && value.length <= 14) {
            setAbha(value);
        }
    }


    return (
        <div className='register'>
            <div className="switch_register">
                <button onClick={() => { setPatient(true) }}>Citizen Register</button>
                <button onClick={() => { setPatient(false) }}>Doctor/Hospital Register</button>
            </div>

            <div className='register_box'>

                <form className={`patient_register ${patient ? "show" : "hide"}`}>
                    <label htmlFor="name">Name:</label>
                    <input type="text" id="name" placeholder="First  Middle  Surname" required /><br />

                    <label htmlFor="email">Email:</label>
                    <input type="email" name="email" id="email" placeholder="example@gmail.com" required /><br />

                    <label htmlFor="contact">Contact No.:</label>
                    <input type="tel" name="contact" id="contact" required /><br />

                    <label htmlFor="aadhaar">Aadhaar No.:</label>
                    <input
                        type="text"
                        id="aadhaar"
                        name="aadhaar"
                        value={aadhaar}
                        onChange={addhaarChange}
                        placeholder="Enter 12-digit Aadhaar"
                        maxLength={12}
                        required
                    /><br />

                    <label htmlFor="address">Address:</label><br />
                    <textarea name="address" id="address" cols="30" rows="10"></textarea><br />

                    <label htmlFor="abha">ABHA No.:</label>
                    <input
                        type="text"
                        id="abha"
                        name="abha"
                        value={abha}
                        onChange={abhaChange}
                        placeholder="Enter 14-digit ABHA"
                        maxLength={14}
                    /><br />

                    <input type="button" value="Register" />
                    <input type="reset" value="Clear" />
                </form>

                <form className={`doctor_hospital_register ${!patient ? "show" : "hide"}`}>

                    <label htmlFor="category">Catgory</label>
                    <select name="category" id="category" onChange={(e) => { setCategory(e.target.value) }}>
                        <option value="Doctor">🩺Doctor</option>
                        <option value="Hospital">🏥Hospital</option>
                    </select><br />

                    <label htmlFor="institution">Name/Institution:</label>
                    <input type="text" id="institution" placeholder={category === "Doctor" ? "Full Name" : " Hospital Name"} required /><br />

                    <label htmlFor="email">Email:</label>
                    <input type="email" name="email" id="email" placeholder={category === "Doctor" ? "doctor@gmail.com" : "contact@hospitalname.in"} required /><br />

                    <label htmlFor="contact">Contact No.:</label>
                    <input type="tel" name="contact" id="contact" required /><br />

                    <label htmlFor="reg_no">Registration No.:</label>
                    <input type="text" id="reg_no" name="reg_no" placeholder={category === "Doctor" ? "Medical Council" : "Hospital Reg. No."} required /><br />

                    <label htmlFor="license">License/Accreditation ID:</label>
                    <input type="text" id="license" name="license" placeholder={category === "Doctor" ? "Govt. License" : "Accreditation ID"} /><br />

                    {category === "Doctor" &&
                        <>
                            <label htmlFor="specialization">Specialization (for doctors):</label>
                            <input type="text" id="specialization" name="specialization" placeholder="e.g. Cardiology, Pediatrics" /><br />
                        </>
                    }

                    <label htmlFor="address">{category === "Doctor" ? "Clinic" : "Hospital"} Address:</label><br />
                    <textarea name="address" id="address" cols="30" rows="10" required></textarea><br />

                    <input type="button" value="Register" />
                    <input type="reset" value="Clear" />
                </form>

                <div>
                    <Link to={"/login"}>Already Registered ? Login here</Link>
                </div>
            </div>
        </div>
    )
}

export default Register