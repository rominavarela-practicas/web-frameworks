import Link from 'next/link'
import styles from './Card.module.scss'

export default function Card({ title, href, image,children }) {
  const className = image ? styles.cardContainerXl : styles.cardContainerSm;

  return (
    <div className={className}>
        <div className={styles.cardContainer}>
          {
            image && (<div className={styles.card}>{image}</div>)
          }
          <div className={styles.card}>
              <h1>{ title }</h1>
              { children }
              <Link href={ href }>
                Test it out <span className={styles.slide}> ➜ </span>
              </Link>
          </div>
        </div>
    </div>
  )
}