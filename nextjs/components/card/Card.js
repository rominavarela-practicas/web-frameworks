import Link from 'next/link'
import styles from './Card.module.scss'

export default function Card({ title, href, children }) {
  
  return (
    <div className={styles.cardContainer}>
      <div className={styles.card}>
          <h1>{ title }</h1>
          { children }
          <Link href={ href }>
            Test it out <span className={styles.slide}> ➜ </span>
          </Link>
      </div>
    </div>
  )
}