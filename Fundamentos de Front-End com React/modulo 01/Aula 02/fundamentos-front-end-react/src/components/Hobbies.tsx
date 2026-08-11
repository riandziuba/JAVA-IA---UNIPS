// "use client" // é usado para rodar em client side
const hobbies = ['Jogos', 'Academia', 'Livros'];

export const Hobbies = () => {
    const [favoriteHobby, ...otherHobbies] = hobbies;

    const newHobbies = ['Musica', 'Viajar'];
    const myHobbies = [
        ...otherHobbies,
        ...newHobbies
    ]
    return (
        <div>
            <p>Estes são meus Hobbies:</p>
            <ul className="list-disc pl-10">
                <li className="font-bold">{favoriteHobby}</li>
                {myHobbies.map((myHobby, i) => <li key={`myHobby-${i}`}>{myHobby}</li>)}
            </ul>
        </div>
    )
};