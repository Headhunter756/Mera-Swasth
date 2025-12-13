import '../styles/Card.css'

const Card = (props) => {

    return (
        <div className='card'>
            <img src={props.src} alt={props.alt} />
            <div className="description">{props.desc}</div>
        </div>
    )
}

export default Card