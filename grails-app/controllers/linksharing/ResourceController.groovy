package linksharing

class ResourceController {
    def beforeInterceptor = {
        String userName= session['userName']
       User user=User.findByUserName(userName)

        if(user==null)
        {   flash.message="You Need to be login First"
            redirect(controller: 'login',action: 'index')
            return false
        }
        Resource resource =Resource.findById(params['resource'])
        def sub=Subscription.findByUserAndTopic(user,resource.topic)

        if(user.admin==false) {

            if(resource.topic.visibility == Visibility.PRIVATE && sub==null || resource==null)
            {   flash.message="Access Rights Issue."
                redirect(controller: 'home',action: 'dashboard')
                return false
            }
        }

    }
    SupportService supportService
  def viewPost()
 {
     User user=User.findByUserName(session['userName'])
     Resource resource =Resource.findById(params['resource'])


     boolean access=false
     if(user.admin==true || user==resource.createdBy)
     { access=true }
     int usersRate=ResourceRating.countByResource(resource)
         [resource:resource,access:access,usersRate:usersRate,trendingTopics:supportService.trending_topic(),user:user]

 }
def rate()
{   User user=User.findByUserName(session['userName'])
    Resource resource =Resource.findById(params['resource'])
    ResourceRating resourceRating=ResourceRating.findByUserAndResource(user,resource)

    if(resourceRating!=null)
    {  resourceRating.score=params['rate'].toString().toInteger()
        resourceRating.save(failOnError: true,flush: true)
        }
    else{
        resourceRating=new ResourceRating(user:user,resource: resource,score:params['rate'].toString().toInteger())
        resourceRating.save(failOnError: true,flush: true)
    }
    flash.message="Rate Successfully"
    redirect(controller: 'home',action: 'dashboard')
}
    def delete()
    { Resource resource =Resource.findById(params['resource'])
        if(resource.instanceOf(DocumentResource))
        { DocumentResource documentResource=resource
            String path=documentResource.filePath
            new File(path).delete()
        }

      resource.delete(flush: true)
        flash.message='Resource Deleted'
      redirect(controller: 'home',action: 'dashboard')      }

    def edit()
    {render params }
}
